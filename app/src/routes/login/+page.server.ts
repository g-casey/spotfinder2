import type {Actions} from "./$types";

export const actions: Actions = {
    default: async ({request}) => {
        const data = await request.formData();
        console.log("signing up user", data.get("username"), data.get("email"), data.get("password"));
    }

} satisfies Actions;