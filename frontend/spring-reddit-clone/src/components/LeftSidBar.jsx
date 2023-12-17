'use client';
import React from 'react'
import SideHome from './sidebarComponents/SideHome'
import { usePathname } from 'next/navigation'
import SideCreatePost from './sidebarComponents/SideCreatePost';

export const LeftSidBar = () => {

    const pathname = usePathname();
    switch (pathname) {
        case "/":
            return (
                <section className='leftsidebar custom-scrollbar px-2'>

                    <div className='flex flex-1 flex-col gap-3'>
                        <SideHome />
                    </div>

                </section>
            )

        case "/create-post":
            return (
                <section className='leftsidebar custom-scrollbar px-2'>

                    <div className='flex flex-1 flex-col gap-3'>
                        <SideCreatePost />
                    </div>

                </section>
            )

        default:
            return (
                <section className='leftsidebar custom-scrollbar px-2'>

                    <div className='flex flex-1 flex-col gap-3'>

                    </div>

                </section>
            )
    }

}
