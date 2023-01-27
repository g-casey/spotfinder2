<script lang="ts">
    import {Swiper} from 'swiper/svelte';
    import {EffectCards} from 'swiper';

    import 'swiper/css';
    import 'swiper/css/effect-cards';
    import type {Song} from '$lib/data';
    import CarouselSlide from './CarouselSlide.svelte';

    export let songs: Song[];

    const slideChange = () => {
        const index = this.realIndex;
        const length = this.slides.length;
        const previous = this.previousIndex;

        this.slides[previous].querySelector('audio').pause();
        this.slides[index].querySelector('audio').play();
    }
</script>

<Swiper effect="cards" modules={[EffectCards]} grabCursor={false} on:transitionend={slideChange}>
    {#each songs as song, index}
        <CarouselSlide {song} {index}/>
    {/each}
</Swiper>
