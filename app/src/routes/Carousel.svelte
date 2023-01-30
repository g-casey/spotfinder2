<script lang="ts">
	import {Swiper} from 'swiper/svelte';
	import {EffectCards} from 'swiper';

	import 'swiper/css';
	import 'swiper/css/effect-cards';
	import type {Song} from '$lib/data';
	import CarouselSlide from './CarouselSlide.svelte';
	import {fetchSongs} from '../lib/SongService';

	let fetching = false;

    export let songs: Song[];

    const slideChange = ({detail}) => {
        const swiper = detail[0];
        const index = swiper.realIndex;
        const length = swiper.slides.length;
        const previous = swiper.previousIndex > length ? index - 1 : swiper.previousIndex;

        swiper.slides[previous].querySelector('audio').pause();
        swiper.slides[index].querySelector('audio').play();

        if (length - index < 5 && !fetching) {
            fetching = true;
            fetchSongs({songNames: songs.map((song) => song.name)}).then((data) => {
                songs = [...songs].concat(data);
                swiper.update();
                fetching = false;

                console.log(swiper);
                if (length > 50) {
                    swiper.removeSlide(Array.from(Array(length - 50).keys()));
                }
            });
        }


        console.log(length);
    };
</script>

<Swiper effect="cards" modules={[EffectCards]} grabCursor={false} on:slideChange={slideChange}>
    {#each songs as song, index}
        <CarouselSlide {song}/>
    {/each}
</Swiper>
