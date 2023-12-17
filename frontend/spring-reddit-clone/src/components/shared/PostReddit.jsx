'use client';
import React, { useContext } from 'react'
import Link from 'next/link'
import AuthContext from '@/context/AuthContext';
import { postFormSchema } from '@/lib/form';
import { zodResolver } from '@hookform/resolvers/zod';
import { useForm } from 'react-hook-form';
import { createPost } from '@/actions/post.action';
import { useRouter } from 'next/navigation';

const PostReddit = () => {

  const { authenticationResponse } = useContext(AuthContext);
  const { register, handleSubmit, formState: {errors} } = useForm({ resolver: zodResolver(postFormSchema) });
  const rout = useRouter();


  const submitData  = (data) => {
    createPost(authenticationResponse.authenticationToken, data);
    rout.push('/');
  }

  return (
    <form onSubmit={handleSubmit(submitData)}>
    <div className="space-y-2 rounded-lg bg-dark-4 p-5">
      <div className="border-b border-gray-900/10">
        <h2 className="text-base font-semibold leading-7 text-white">Create Post</h2>

        <div className="mt-10 grid grid-cols-1 gap-x-6 gap-y-4 sm:grid-cols-6">
          <div className="sm:col-span-4">
            <label htmlFor="subredditName" className="block text-sm font-medium leading-6 text-white">
              Community Name
            </label>
            <div className="mt-2">
              <div className="flex rounded-md shadow-sm ring-1 ring-inset ring-gray-300 focus-within:ring-2 focus-within:ring-inset focus-within:ring-indigo-600 sm:max-w-md">
                <span className="flex select-none items-center pl-3 text-gray-500 sm:text-sm">r/</span>
                <input
                {...register("subredditName")}
                  type="text"
                  id="subredditName"
                  autoComplete="subredditName"
                  className="block flex-1 border-0 bg-transparent py-1.5 pl-1 text-white placeholder:text-gray-400 focus:outline-none sm:text-sm sm:leading-6"
                  placeholder="Spring Clone"
                />
              </div>
              {errors.subredditName && <p className='text-white'>{errors.subredditName.message}</p>}
            </div>
          </div>

          <div className="sm:col-span-4">
            <label htmlFor="postName" className="block text-sm font-medium leading-6 text-white">
              Post Name
            </label>
            <div className="mt-2">
              <div className="flex rounded-md shadow-sm ring-1 ring-inset ring-gray-300 focus-within:ring-2 focus-within:ring-inset focus-within:ring-indigo-600 sm:max-w-md">
                <span className="flex select-none items-center pl-3 text-gray-500 sm:text-sm"></span>
                <input
                {...register("postName")}
                  type="text"
                  id="postName"
                  autoComplete="postName"
                  className="block flex-1 border-0 bg-transparent py-1.5 pl-1 text-white placeholder:text-gray-400 focus:outline-none sm:text-sm sm:leading-6"
                  placeholder="Spring Clone"
                />
              </div>
              {errors.postName && <p className='text-white'>{errors.postName.message}</p>}
            </div>
          </div>

          <div className="col-span-full">
            <label htmlFor="description" className="block text-sm font-medium leading-6 text-white">
              Description
            </label>
            <div className="mt-2">
              <textarea
              {...register("description")}
                id="description"
                rows={3}
                className="block w-full rounded-md border-0 p-2 bg-transparent text-white shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                defaultValue={''}
              />
            </div>
            {errors.description && <p className='text-white'>{errors.description.message}</p>}
            <p className="mt-3 text-sm leading-6 text-gray-600">Write a few sentences about yourself.</p>
          </div>

    <div className="mt-6 flex items-center justify-center gap-x-4">
    <button
        type="submit"
        disabled={authenticationResponse.username ? false : true}
        className="rounded-full disabled:bg-gray-500 disabled:cursor-not-allowed bg-reddit-orange px-3 py-2 text-sm font-semibold text-white  shadow-sm border-reddit-orange border hover:bg-transparent hover:text-white focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600"
      >
        Save
      </button>
      <Link 
      href="/"
      className="rounded-full text-reddit-orange px-3 py-2 text-sm font-semibold border border-reddit-orange  shadow-sm hover:bg-reddit-orange hover:text-white focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600">
        Cancel
      </Link>
      
    </div>
    </div>
    </div>
    </div>
  </form>
  )
}

export default PostReddit


/*
  This example requires some changes to your config:
  
  ```
  // tailwind.config.js
  module.exports = {
    // ...
    plugins: [
      // ...
      require('@tailwindcss/forms'),
    ],
  }
  ```
*/


