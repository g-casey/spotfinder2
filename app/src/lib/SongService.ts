import { backendUrl} from "../config";
import type {Song} from "./data";

export type fetchSongProps = {
    songNames?: string[]
};

const fetchSongs = async({songNames} : fetchSongProps) : Promise<Song[]> => {
    const url = new URL(`${backendUrl}/songs/recommendations`);
    const songData = await fetch(backendUrl + `/songs/recommendations`);

    if(songNames) {
        url.searchParams.append("tracks", songNames.join(","));
    }
    return (await songData.json()) as Song[];
}

const fetchGenres = async() : Promise<string[]> => {
    const genreData = await fetch(backendUrl + '/songs/genres') ;
    let genres = (await genreData.json()) as string[];
    genres = genres.filter(genre => genre !== "pop")
        .map(genre => genre[0].toUpperCase() + genre.slice(1));
    genres.unshift("All")
    return genres;
}

export {
    fetchSongs,
    fetchGenres
}