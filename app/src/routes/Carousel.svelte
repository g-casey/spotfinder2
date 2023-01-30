<script lang="ts">
    import {Swiper} from 'swiper/svelte';
    import {EffectCards} from 'swiper';

    import 'swiper/css';
    import 'swiper/css/effect-cards';
    import type {Song} from '$lib/data';
    import CarouselSlide from './CarouselSlide.svelte';
    import {fetchSongs} from "../lib/SongService";

    export let songs: Song[];

    const slideChange = ({detail}) => {
        const swiper = detail[0];
        const index = swiper.realIndex;
        const length = swiper.slides.length;
        const previous = swiper.previousIndex;

        swiper.slides[previous].querySelector('audio').pause();
        swiper.slides[index].querySelector('audio').play();

        if(length - index < 5) {
           fetchSongs({songNames: songs.map((song) => song.name)})
                .then((data) => {
                    songs = [...songs].concat(data)
                    swiper.update();
                });
        }

        if(length > 50) {
            const copy = [...songs];
            copy.splice(0, 20);
            songs = copy;
            swiper.update();
        }
        console.log(length);
    }
</script>

<Swiper effect="cards" modules={[EffectCards]} grabCursor={false} on:slideChange={slideChange} >
    {#each songs as song, index}
        <CarouselSlide {song} />
    {/each}
</Swiper>
