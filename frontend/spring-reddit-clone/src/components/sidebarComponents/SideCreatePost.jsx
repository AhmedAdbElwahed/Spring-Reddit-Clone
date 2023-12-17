import Image from 'next/image'
import React from 'react'

const SideCreatePost = () => {
  return (
    <>
      <div className="bg-dark-4 rounded-lg shadow-md">
        <div>
          <Image
            src="/assets/home-banner.png"
            height={500}
            width={500}
            alt='home-banner'
          />
        </div>
        <div className='mt-[-15px] ml-5'>
          <div className='flex items-center space-x-2'>
            <Image
              src="/assets/post-avatar.png"
              height={60}
              width={60}
              alt='post-avatar'
            />
            <p className="text-white pt-4">Posting To Reddit</p>
          </div>
        </div>
        <div className="flex items-start flex-col p-4">

          <div className='py-2'>
            <p className="text-white text-[13px]">
              1.Remember the human
            </p>
          </div>
          <div className='py-2'>
            <p className="text-white text-[13px]">
              2.Behave like you would in real life
            </p>
          </div>
          <div className='py-2'>
            <p className="text-white text-[13px]">
              3.Look for the original source of content
            </p>
          </div>
          <div className='py-2'>
            <p className="text-white text-[13px]">
              4.Search for duplicates before posting
            </p>
          </div>
          <div className='py-2'>
            <p className="text-white text-[13px]">
              5.Read the community's rules
            </p>
          </div>
        </div>
      </div>
      <div className='py-2'>
        <p className="text-gray-400 text-[13px]">
        Please be mindful of reddit's <span className='text-blue'>content policy</span> and practice good <span className='text-blue'>reddiquette.</span>
        </p>
      </div>
    </>
  )
}

export default SideCreatePost