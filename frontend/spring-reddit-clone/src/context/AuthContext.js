'use client';
import { createContext, useEffect, useState } from "react";
import axios from "axios";
import { toast } from "react-toastify";
import { useRouter } from "next/navigation";


const AuthContext = createContext();

export default AuthContext;

export const AuthProvider = ({ children }) => {
    // const [user, setUser] = useState(localStorage.getItem("username"));
    // const [authTokens, setAuthTokens] = useState(localStorage.getItem("authenticationToken"));
    const rout = useRouter();

    const [loading, setLoading] = useState(true);

    const [authenticationResponse, setAuthenticationResponse] = useState(
        JSON.parse(localStorage.getItem("authenticationResponse")) == null ? {} :
            JSON.parse(localStorage.getItem("authenticationResponse")));

    const loginUser = async (data) => {
        await axios.post("http://localhost:8080/api/auth/login", {
            username: data.username,
            password: data.password
        }).then(res => {
            console.log(res);
            localStorage.setItem('authenticationResponse', JSON.stringify(res.data));
            setAuthenticationResponse(res.data);
            toast.success("You logged in successfully");
            rout.push('/');
        }).catch(err => {
                console.log(err);
                toast.error("Logged in failed");
            });
    }

    const logout = async () => {
        await axios.post("http://localhost:8080/api/auth/logout", {
            refreshToken: authenticationResponse.refreshToken,
            username: authenticationResponse.username
        }
        ).then(() => {
            localStorage.removeItem("authenticationResponse");
            setAuthenticationResponse({});
            toast.success("You logged out successfully");
        }).catch(err => {
            console.log(err);
            toast.error("Logout failed");

        })
    }

    const updateToken = async () => {
        console.log("update is called");
        if (authenticationResponse.refreshToken) {
            await axios.post('http://localhost:8080/api/auth/refresh/token', {
                refreshToken: authenticationResponse.refreshToken,
                username: authenticationResponse.username
            }).then(res => {
                localStorage.setItem('authenticationResponse', JSON.stringify(res.data));
                setAuthenticationResponse(res.data);
                setLoading(false);
            }).catch(() => {
                rout.push("/");
            })
        }
    }

    let contextData = {
        // user,
        authenticationResponse,
        loginUser,
        logout
    }

    useEffect(() => {
        if (loading) {
            updateToken();
        }
        const interval = setInterval(() => {
            if (authenticationResponse.authenticationToken) {
                updateToken();
            }
        }, 900000)
        return () => clearInterval(interval);
    }, [authenticationResponse.authenticationToken, loading])


    return (
        <AuthContext.Provider value={contextData}>
            {children}
        </AuthContext.Provider>
    )
}