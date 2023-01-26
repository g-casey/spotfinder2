<script lang="ts">
	import type { Song } from '$lib/data';
	import { backendUrl } from '../config';
	import type { PageData } from './$types';
	import Carousel from './Carousel.svelte';
	import Circle from 'svelte-loading-spinners/Circle.svelte';

	export let data: PageData;

	const fetchSongs = async () => {
		const songData = await fetch(backendUrl + '/songs/recommendations');
		return (await songData.json()) as Song[];
	};
</script>

<div class="container h-full mx-auto max-w-xl">
	<div class="flex gap-4 max-w-sm mx-auto pt-12 mb-10">
		<select>
			<option>All</option>
			<option>Low</option>
			<option>Medium</option>
			<option>High</option>
		</select>
		<select>
			{#each data.genres as genre}
				<option value={genre}>{genre}</option>
			{/each}
		</select>
	</div>
	{#await fetchSongs()}
		<div class="flex justify-center items-center max-w-xl h-1/2">
			<Circle color="var(--color-primary-500)" />
		</div>
	{:then songs}
		<Carousel {songs} />
	{:catch error}
		<h2 class="text-2xl font-semibold">
			Failed to fetch songs from Spotify. This may be due to the spotify api being down.
		</h2>
	{/await}
</div>
