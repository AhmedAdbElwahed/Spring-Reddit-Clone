import axios from "axios"

export const fetchAllSubreddit = async (token) => {
    return axios.get("http://localhost:8080/api/posts").then((res) => res.data
    ).catch((err) => console.log(err));;
}