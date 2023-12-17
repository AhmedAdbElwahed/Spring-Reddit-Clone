import { toast } from "react-toastify";

export const logout = () => {
    localStorage.removeItem("authenticationToken");
    localStorage.removeItem("expiresAt");
    localStorage.removeItem("refreshToken");
    localStorage.removeItem("username");
    
}