import type { Song } from "$lib/data";
import {backendUrl} from "../config";
import type { PageLoad } from './$types';


export const load = (async ({fetch}) => {
    const genreData = await fetch(`${backendUrl}/songs/genres`);
    let genres = await genreData.json() as string[];
    genres = genres.filter(genre => genre !== "pop");
    genres.unshift("All")



    return {
        genres,
    }
}) satisfies PageLoad;