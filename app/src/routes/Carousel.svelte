<script lang="ts">
	import { Swiper } from 'swiper/svelte';
	import { EffectCards } from 'swiper';

	import 'swiper/css';
	import 'swiper/css/effect-cards';
	import type { Song } from '$lib/data';
	import CarouselSlide from './CarouselSlide.svelte';

	export let songs: Song[];
</script>

<Swiper effect="cards" modules={[EffectCards]} grabCursor={true}>
	{#each songs as song}
		<CarouselSlide {song} />
	{/each}
</Swiper>
