<script>
	import '@skeletonlabs/skeleton/themes/theme-hamlindigo.css';
	//import '@skeletonlabs/skeleton/themes/theme-gold-nouveau.css'
	//import '@skeletonlabs/skeleton/themes/theme-rocket.css'
	import '@skeletonlabs/skeleton/styles/all.css';
	import '../app.postcss';
	import { AppBar, AppShell } from '@skeletonlabs/skeleton';

	export const prerender = true;
</script>

<AppShell slotSidebarLeft="bg-surface-500/5 w-56 p-4">
	<svelte:fragment slot="header">
		<AppBar>
			<svelte:fragment slot="lead">
				<strong class="text-xl">Spotfinder</strong>
			</svelte:fragment>
			<svelte:fragment slot="trail">
				<button class="btn variant-filled-primary btn-base"> Login </button>
			</svelte:fragment>
		</AppBar>
	</svelte:fragment>
	<slot />
</AppShell>
