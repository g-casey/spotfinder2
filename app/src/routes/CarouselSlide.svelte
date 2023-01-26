<script lang="ts">
	import type { Song } from '$lib/data';
	import { onMount } from 'svelte';
	import { SwiperSlide } from 'swiper/svelte';

	import ChevronLeft from 'svelte-material-icons/ChevronLeft.svelte';
	import ChevronRight from 'svelte-material-icons/ChevronRight.svelte';
	import Play from 'svelte-material-icons/Play.svelte';
	import Pause from 'svelte-material-icons/Pause.svelte';
	import 'swiper/css/virtual';

	export let song: Song;

	let preview: HTMLAudioElement;
	let playing = false;

	onMount(() => {
		preview.volume = 0.05;
	});
</script>

<SwiperSlide class="rounded-xl shadow-sm p-0 sm:py-16 transition-all text-slate-100">
	<div
		class="blur-xl brightness-[70%] h-screen w-screen bg-fixed bg-center bg-no-repeat bg-cover fixed -top-6 -left-6 -z-50 slide"
		style={`background-image: url("${song.album.imageUrl}");`}
	/>
	<div class="h-full w-full flex flex-col justify-center items-center gap-3">
		<button
			class="w-7/12 flex justify-center items-center group"
			on:click={() => (playing = !playing)}
		>
			<img
				src={song.album.imageUrl}
				alt="album cover"
				class="rounded-sm shadow-md group-hover:brightness-[20%] group-hover:blur-[1px] group-hover:contrast-75 group-hover:transition-all group-hover:duration-500"
			/>
			{#if !playing}<span
					class="absolute opacity-0 group-hover:opacity-100 material-icons text-white text-5xl"
					><Play size="4rem" /></span
				>
			{:else}
				<span class="absolute opacity-0 group-hover:opacity-100 material-icons text-white text-5xl"
					><Pause size="4rem" /></span
				>
			{/if}
		</button>
		<div class="w-7/12 flex items-center justify-around">
			<button class="px-1 text-white absolute top-0 left-4 bottom-0 h-[39px] m-auto">
				<ChevronLeft size="2.5rem" />
			</button>
			<h2
				class="font-medium text-2xl text-center overflow-hidden text-ellipsis max-h-24 line-clamp-2"
			>
				{song.name}
			</h2>
			<button class="px-1 text-white absolute top-0 right-4 bottom-0 h-[39px] m-auto">
				<ChevronRight size="2.5rem" />
			</button>
		</div>
		<p class="font-light">{song.artists.map((artist) => artist.name).join(', ')}</p>
	</div>
	<audio crossOrigin="anonymous" loop bind:this={preview} on:play={() => (playing = true)}>
		<source src={''} type="audio/mpeg" />
	</audio>
</SwiperSlide>

<style lang="postcss">
</style>
