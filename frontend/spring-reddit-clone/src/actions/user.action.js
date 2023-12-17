import axios from "axios"

export const signUp = async (data) => {
    axios.post("http://localhost:8080/api/auth/signup", {
        email: data.email,
        username: data.username,
        password: data.password
    }).catch(err => console.log(err))
}


