
import { fetchAllPosts } from "@/actions/post.action";
import PostCard from "@/components/card/PostCard";



import React from 'react'

const Home = async () => {



  const posts = await fetchAllPosts();
  

  return (
    <section className="mt-9 flex flex-col gap-10">
      {posts.length === 0 ? (
          <p className='no-result'>No threads found</p>
        ) : (
          <>
          {
            posts.map((post) => (
              <PostCard key={post.id} post={post}/>
            ))
          }
          </>
        )}

    </section>
  )
}

export default Home
