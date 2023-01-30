import type { Song } from "$lib/data";
import {backendUrl} from "../config";
import type { PageLoad } from './$types';
import {fetchGenres} from "../lib/SongService";

export const prerender = true;

export const load = (async ({fetch}) => {
    const genres = await fetchGenres();

    return {
        genres,
    }
}) satisfies PageLoad;