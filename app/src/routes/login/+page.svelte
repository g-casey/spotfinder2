<script lang="ts">

</script>


<form class="card p-10 shadow-md sm:max-w-md mx-auto sm:mt-[10%]" method="POST">
    <h2 class="text-center mb-6 font-semibold">Sign Up</h2>
    <label>
        <span>Email:</span>
        <input type="email" name="email" placeholder="email@email.com" required/>
    </label>
    <label>
        <span>Username:</span>
        <input type="text" name="username" placeholder="username" required/>
    </label>
    <label>
        <span>Password:</span>
        <input type="password" name="password" placeholder="password" required/>
    </label>
    <button class="btn btn-base btn-lg variant-filled-primary w-full mt-4">Sign Up</button>

    <!--<span class="mt-3">
        Already have an account? <a>Login</a>
    </span>-->
</form>

<style lang="postcss">
    label {
        @apply flex flex-col gap-1 mb-3;
    }


</style>