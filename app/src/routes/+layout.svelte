<script>
    import '@skeletonlabs/skeleton/themes/theme-hamlindigo.css';
    //import '@skeletonlabs/skeleton/themes/theme-gold-nouveau.css'
    //import '@skeletonlabs/skeleton/themes/theme-rocket.css'
    import '@skeletonlabs/skeleton/styles/all.css';
    import '../app.postcss';
    import {AppBar, AppShell} from '@skeletonlabs/skeleton';
    import {page} from "$app/stores";
</script>

<AppShell slotSidebarLeft="bg-surface-500/5 w-56 p-4">
    <svelte:fragment slot="header">
        <AppBar>
            <svelte:fragment slot="lead">
                <a class="text-xl pointer-cursor font-bold" href="/">Spotfinder</a>
            </svelte:fragment>
            <svelte:fragment slot="trail">
                {#if $page.route.id !== "/login"}
                    <a class="btn variant-filled-primary btn-base" href="/login"> Login</a>
                {/if}
            </svelte:fragment>
        </AppBar>
    </svelte:fragment>
    <slot/>
</AppShell>
