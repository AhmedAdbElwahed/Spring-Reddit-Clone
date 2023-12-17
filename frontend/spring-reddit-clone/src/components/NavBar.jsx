'use client';
import React, { Fragment, useContext} from 'react'
import { Disclosure, Menu, Transition } from '@headlessui/react'
import { Bars3Icon, BellIcon, XMarkIcon, MagnifyingGlassIcon } from '@heroicons/react/24/outline'
import Image from 'next/image';
import Link from 'next/link';
import { usePathname} from 'next/navigation';
import AuthContext from '@/context/AuthContext';


function classNames(...classes) {
    return classes.filter(Boolean).join(' ')
}


const NavBar = () => {

    const { authenticationResponse, logout } = useContext(AuthContext);
    console.log(authenticationResponse.username);
    const path = usePathname();

    const handleLogout = () => {
        logout();
    }

    return (
        <Disclosure as="nav" className="topbar">
            {({ open }) => (
                <>
                    <div className="mx-auto w-11/12 px-2 sm:px-6 lg:px-8">
                        <div className="relative flex py-2 items-center justify-between">
                            <div className="flex flex-1 sm:items-stretch sm:justify-start">
                                <div className="flex flex-shrink-0 items-center">
                                    <Link
                                        href={"/"}
                                    >
                                        <Image
                                            className="h-8 w-auto"
                                            src="/assets/Reddit logo.svg"
                                            height={46}
                                            width={46}
                                            alt="Your Company"
                                        />
                                    </Link>
                                </div>
                                <div className="hidden sm:ml-6 sm:block w-full">
                                    {
                                        (path != '/login' && path != '/signup') && (
                                            <form className='flex justify-center'>
                                                <div className='flex w-3/4 h-10  rounded-full p-1 bg-slate-600 text-sm'>
                                                    <MagnifyingGlassIcon className='h-5 w-5 text-slate-400 my-2 mx-2' />
                                                    <input
                                                        className='w-11/12 h-auto bg-slate-600 focus:outline-none'
                                                        placeholder='Search Reddit' />
                                                </div>


                                            </form>
                                        )
                                    }

                                </div>
                            </div>
                            <div className="absolute inset-y-0 right-0 flex items-center pr-2 sm:static sm:inset-auto sm:ml-6 sm:pr-0">
                                {
                                authenticationResponse.username != null ? (
                                        <>
                                            <button
                                                type="button"
                                                className="relative rounded-full bg-gray-800 p-1 text-reddit-orange hover:text-white focus:outline-none focus:ring-2 focus:ring-white focus:ring-offset-2 focus:ring-offset-gray-800"
                                            >
                                                <span className="absolute -inset-1.5" />
                                                <span className="sr-only">View notifications</span>
                                                <BellIcon className="h-6 w-6" aria-hidden="true" />
                                            </button>

                                            {/* Profile dropdown */}
                                            <Menu as="div" className="relative ml-3">
                                                <div>
                                                    <Menu.Button className="relative flex rounded-full bg-gray-800 text-sm focus:outline-none focus:ring-2 focus:ring-white focus:ring-offset-2 focus:ring-offset-gray-800">
                                                        <span className="absolute -inset-1.5" />
                                                        <span className="sr-only">Open user menu</span>
                                                        <img
                                                            className="h-8 w-8 rounded-full"
                                                            src="https://images.unsplash.com/photo-1472099645785-5658abf4ff4e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=facearea&facepad=2&w=256&h=256&q=80"
                                                            alt=""
                                                        />
                                                    </Menu.Button>
                                                </div>
                                                <Transition
                                                    as={Fragment}
                                                    enter="transition ease-out duration-100"
                                                    enterFrom="transform opacity-0 scale-95"
                                                    enterTo="transform opacity-100 scale-100"
                                                    leave="transition ease-in duration-75"
                                                    leaveFrom="transform opacity-100 scale-100"
                                                    leaveTo="transform opacity-0 scale-95"
                                                >
                                                    <Menu.Items className="absolute right-0 z-10 mt-2 w-48 origin-top-right rounded-md bg-white py-1 shadow-lg ring-1 ring-black ring-opacity-5 focus:outline-none">
                                                        <Menu.Item>
                                                            {({ active }) => (
                                                                <Link
                                                                    href="#"
                                                                    className={classNames(active ? 'bg-gray-100' : '', 'block px-4 py-2 text-sm text-gray-700 text-center')}
                                                                >
                                                                    Your Profile
                                                                </Link>
                                                            )}
                                                        </Menu.Item>
                                                        <Menu.Item>
                                                            {({ active }) => (
                                                                <Link
                                                                    href="#"
                                                                    className={classNames(active ? 'bg-gray-100' : '', 'block px-4 py-2 text-sm text-gray-700 text-center')}
                                                                >
                                                                    Settings
                                                                </Link>
                                                            )}
                                                        </Menu.Item>
                                                        <Menu.Item>
                                                            {({ active }) => (
                                                                <Link
                                                                    onClick={handleLogout}
                                                                    href={"/"}
                                                                    className={classNames(active ? 'bg-gray-100' : '', 'block px-4 py-2 text-sm text-gray-700 w-full text-center')}

                                                                >
                                                                    Sign out
                                                                </Link>
                                                            )}
                                                        </Menu.Item>
                                                    </Menu.Items>
                                                </Transition>
                                            </Menu>
                                        </>
                                    ) : (
                                        <>
                                            {
                                                path != '/login' && (
                                                    <Link
                                                        href={'/login'}
                                                        className="relative rounded-full h-10 pt-2 px-2 mx-1 bg-[#FF4500] text-white hover:bg-orange-600"
                                                    >
                                                        Log In
                                                    </Link>
                                                )
                                            }
                                            {
                                                path != '/signup' && (
                                                    <Link
                                                        href={'/signup'}
                                                        className="relative rounded-full h-10 pt-2 px-2 mx-1 bg-[#FF4500] text-white hover:bg-orange-600"
                                                    >
                                                        Sign Up
                                                    </Link>
                                                )
                                            }

                                        </>
                                    )
                                }

                            </div>
                        </div>
                    </div>
                </>
            )}
        </Disclosure>
    )
}

export default NavBar;
