import { z } from "zod";

export const loginFormSchema = z.object({
    username: z.string().min(3, "too few character"),
    password: z.string().min(8, "password is not sufficient")
});

export const singUpFormSchema = z.object({
    username: z.string().min(3, "too few character"),
    email: z.string().email(),
    password: z
    .string()
    .min(8, "too few character"),
    confirmPassword: z.string().min(8)
}).refine((data) => data.password === data.confirmPassword, {
    message: "Password does't matches",
    path: ["confirmPassword"]
});

export const postFormSchema = z.object({
    postName: z.string().min(1, "the field can't be empty").max(300, "the max number of character is 300"),
    subredditName: z.string().min(1, "the field cant be empty"),
    description: z.string().min(0)
})