import React from 'react'
import {  ArrowSmallDownIcon,
    ArrowSmallUpIcon } from '@heroicons/react/24/solid'

const VoteButton = ({voteCount}) => {
  return (
    <div className="flex items-center space-x-2">
    <button className="flex justify-center items-center gap-2 px-1 hover:text-white rounded-full p-1">
        <ArrowSmallUpIcon className='w-5 h-5 text-base-semibold hover:text-green-600' />
    </button>
    <span>{voteCount} Votes</span>
    <button className="flex justify-center items-center gap-2 px-1 hover:text-white rounded-full p-1">
        <ArrowSmallDownIcon className='w-5 h-5 hover:text-red-600' />
    </button>
</div>
  )
}

export default VoteButton