import axios from "axios"

export const fetchAllPosts = async () => {
    return axios.get("http://localhost:8080/api/posts"
    ).then((res) => res.data
    ).catch((err) => console.log(err));
}

export const createPost = async (token, post) => {
    axios.post("http://localhost:8080/api/posts", {
        "postName": post.postName,
        "subredditName": post.subredditName,
        "url": post.url,
        "description": post.description
    }, {
        headers: {
            Authorization: `Bearer ${token}`
        }
    })
    .then((res) => console.log(res.data))
    .catch((err) => console.log(err));
}