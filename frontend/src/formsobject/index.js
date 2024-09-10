import { z } from "zod";

export const adminLogin = z.object({
  email: z.string().email({ message: "Not a valid email" }),
  password: z.string().min(8, { message: "Password must be minimum 8 Digit" }),
});

const ACCEPTED_IMAGE_TYPES = [
  "image/jpeg",
  "image/jpg",
  "image/png",
  "image/webp",
];

export const AddBlogObject = z.object({
  title: z.string().min(4, { message: "Minimum 4 digit" }),
  content: z
    .string()
    .min(4, { message: "minimum 4 digit" })
    .max(255, { message: "maximum 255 words" }),
  images: z
    .instanceof(FileList, { message: "image is required" })
    .refine((file) => file.length >= 1, { message: "image is required" })
    .refine((filetype) => ACCEPTED_IMAGE_TYPES.includes(filetype[0]?.type), {
      message: "Not a valid image",
    })
    .refine((file) => file[0]?.size <= 1000000, {
      message: `Max image size is 1mb.`,
    }),
});

export const UpdateBlog = z.object({
  title: z.string().min(4, { message: "Minimum 4 digit" }),
  content: z
    .string()
    .min(4, { message: "minimum 4 digit" })
    .max(255, { message: "maximum 255 words" }),
});
