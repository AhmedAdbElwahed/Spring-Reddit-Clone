import Image from 'next/image'
import Link from 'next/link'
import React from 'react'

const SideHome = () => {
  return (
    <>
    <div className="bg-dark-4  py-4 rounded-lg shadow-md">
                    <div className="flex items-center flex-col">
                        <div className="flex items-center space-x-2 w-full ml-4">
                            <img src="https://i.redd.it/88qjrepdhng61.jpg" alt="User Avatar" className="w-8 h-8 rounded-full" />
                            <div>
                                <p className="text-white text-[12px]">Reddit Premium</p>

                                <p className="text-gray-500 text-[11px]">The best Reddit experience</p>
                            </div>
                        </div>
                        <div className="flex justify-center text-gray-500 cursor-pointer mt-2 w-full">
                            {/* <!-- Three-dot menu icon --> */}
                            <button className=" bg-reddit-orange hover:bg-reddit-orange/90 text-white rounded-full p-1 w-10/12">
                                Try Now
                            </button>
                        </div>
                    </div>
                </div>
                {/* the second panel */}
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
                            src="/assets/snoo-home.png"
                            height={38}
                            width={38}
                            alt="snoo-home"
                        />
                        <p className="text-white pt-4">Home</p>
                        </div>
                    </div>
                    <div className="flex items-center flex-col">

                        <div className="flex items-center space-x-2 w-full ml-4 ">
                            <div className='px-2'>
                                <p className="text-white text-[13px]">
                                    Your personal Reddit frontpage. Come here to check in with your favorite communities.
                                </p>
                            </div>
                        </div>
                        <div className="flex flex-col gap-2 justify-center items-center text-gray-500 cursor-pointer my-2 w-full">
                            <Link 
                            href='/create-post'
                            className=" bg-reddit-orange hover:bg-reddit-orange/90 text-white text-center rounded-full w-10/12 p-1">
                                Create Post
                            </Link>
                            <Link
                            href='/create-subreddit'
                            className=" bg-blue hover:bg-blue/90 text-center text-white rounded-full w-10/12 p-1">
                                create Community
                            </Link>
                        </div>
                    </div>
                </div>
                {/* the third panel */}
                <div className='bg-dark-4 rounded-lg shadow-md'>
                    <div className='flex flex-col gap-1 p-4'>
                        <div  className='flex flex-row gap-9'>
                            <p className='text-[12px] w-fit'>User Agreement</p>
                            <p className='text-[12px] w-fit'>Content Policy</p>
                        </div>
                        <div className='flex flex-row gap-9'>
                            <p className='text-[12px] w-fit'>Privacy Policy</p>
                            <p className='text-[12px]'>Moderator Code Of Conduct</p>
                        </div>
                        <hr className='my-3'/>
                        <div>
                            <p className='text-[12px]'>
                            Spring Reddit, Inc. © 2023. All rights reserved.
                            </p>
                        </div>
                    </div>
                </div>
    </>
  )
}

export default SideHome