'use client';
import React, { useContext } from 'react'
import Image from 'next/image'
import { useForm } from 'react-hook-form';
import { zodResolver } from '@hookform/resolvers/zod';
import { loginFormSchema } from '@/lib/form';
import AuthContext from '@/context/AuthContext';

const page = () => {
    const {loginUser} = useContext(AuthContext);

    const { register, handleSubmit, formState: {errors} } = useForm({ resolver: zodResolver(loginFormSchema) });

    const submitData = (data) => {
        console.log("It worked");
        loginUser(data);
    }

    return (
        <>
            <div className="flex flex-col justify-center px-6 py-32">
                <Image
                    className="h-8 w-auto"
                    src="/assets/Reddit logo.svg"
                    height={46}
                    width={46}
                    alt="Your Company"
                />
                <div className="sm:mx-auto sm:w-full sm:max-w-sm">

                    <h2 className="mt-10 text-center text-sm leading-9 text-white">
                        By continuing, you agree to our&nbsp;
                        <a href="#" className="leading-9 text-indigo-600 hover:text-indigo-500">
                            User Agreement
                        </a>

                        &nbsp;and acknowledge that you understand the&nbsp;
                        <a href="#" className="leading-9 text-indigo-600 hover:text-indigo-500">
                            Privacy Policy
                        </a>

                    </h2>
                </div>

                <div className="mt-10 sm:mx-auto sm:w-full sm:max-w-sm">
                    <form className="space-y-6" onSubmit={handleSubmit(submitData)} method="POST">
                        <div>
                            <label htmlFor="username" className="block text-sm font-medium leading-6 text-white">
                                Username
                            </label>
                            <div className="mt-2">
                                <input
                                {...register("username")}
                                    id="username"
                                    type="text"
                                    autoComplete="username"
                                    required
                                    className="block w-full rounded-md border-0 p-1.5 text-white shadow-sm ring-1 ring-inset bg-slate-600 focus:outline-none placeholder:text-gray-400 sm:text-sm sm:leading-6"
                                />
                            </div>
                            {errors.username && <span>{errors.username.message}</span>}
                        </div>

                        <div>
                            <div className="flex items-center justify-between">
                                <label htmlFor="password" className="block text-sm font-medium leading-6 text-white">
                                    Password
                                </label>
                                <div className="text-sm">
                                    <a href="#" className="font-semibold text-indigo-600 hover:text-indigo-500">
                                        Forgot password?
                                    </a>
                                </div>
                            </div>
                            <div className="mt-2">
                                <input
                                {...register("password")}
                                    id="password"
                                    name="password"
                                    type="password"
                                    autoComplete="current-password"
                                    required
                                    className="block w-full rounded-md border-0 p-1.5 text-white shadow-sm ring-1 ring-inset bg-slate-600 focus:outline-none placeholder:text-gray-400 sm:text-sm sm:leading-6"
                                />
                            </div>
                            {errors.password && <span>{errors.password.message}</span>}
                        </div>

                        <div>
                            <input
                                type="submit"
                                value="Login"
                                className="flex w-full justify-center rounded-md bg-indigo-600 px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600"
                            />
                        
                        </div>
                    </form>

                    <p className="mt-10 text-center text-sm text-white">
                        Not a member?{' '}
                        <a href="#" className="font-semibold leading-6 text-indigo-600 hover:text-indigo-500">
                            Start a 14 day free trial
                        </a>
                    </p>
                </div>
            </div>
        </>
    )
}

export default page