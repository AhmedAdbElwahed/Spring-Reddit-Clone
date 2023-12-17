import React from 'react'
import {
    ChatBubbleOvalLeftEllipsisIcon,
    EllipsisVerticalIcon,
} from '@heroicons/react/24/solid'
import Link from 'next/link'
import VoteButton from '../shared/VoteButton'

const PostCard = ({ post }) => {
    return (
        // <!-- component -->
        <div className="bg-dark-4  p-4 rounded-lg shadow-md">
            <Link href={`/post/${post.id}`}>
                {/* <!-- User Info with Three-Dot Menu --> */}
                <div className="flex items-center justify-between mb-4">
                    <div className="flex items-center space-x-2">
                        <img src="https://i.redd.it/88qjrepdhng61.jpg" alt="User Avatar" className="w-8 h-8 rounded-full" />
                        <div>
                            <Link href={`/r/${post.subredditName}`}>
                                <p className="text-white font-semibold">r/{post.subredditName}</p>
                            </Link>
                            <p className="text-gray-500 text-sm">Posted {post.duration}</p>
                        </div>
                    </div>
                    <div className="text-gray-500 cursor-pointer">
                        {/* <!-- Three-dot menu icon --> */}
                        <button className=" hover:bg-reddit-orange hover:text-white rounded-full p-1">
                            <EllipsisVerticalIcon className='w-5 h-5' />
                        </button>
                    </div>
                </div>
                {/* <!-- Message --> */}
                <div className="mb-4">
                    <p className="text-white">{post.description}</p>
                </div>
                {/* <!-- Like and Comment Section --> */}
                <div className="flex items-center justify-between text-gray-500">
                <VoteButton voteCount={post.voteCount}/>
                    <button className="flex justify-center items-center gap-2 px-2 hover:bg-reddit-orange hover:text-white rounded-full p-1">
                        <ChatBubbleOvalLeftEllipsisIcon className='w-5 h-5' />
                        <span>{post.commentCount} Comment</span>
                    </button>
                </div>
            </Link>

        </div>
    )
}

export default PostCard

// 2:27:33