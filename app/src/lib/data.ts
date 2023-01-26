export type Artist = {
    id: string,
    name: string, 
    href: string
}

export type Album = {
    name: string, 
    imageUrl: string
}

export type Song = {
    id: string, 
    name: string, 
    artists: Artist[],
    href: string,
    album: Album,
    isPlayable: boolean,
    popularity: number,
    externalUrl: string,
    previewUrl: string
}

