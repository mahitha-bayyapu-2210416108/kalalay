<template>
<div>
    <img
      :src="logo"
      :alt="logo"
      class="w-full h-64 object-cover transition duration-300 group-hover:blur-sm"
    />
    <div
      class="absolute inset-0 flex flex-col justify-end bg-black bg-opacity-50 opacity-0 group-hover:opacity-100 transition-opacity duration-300 p-4"
    >
      <!-- Title -->
      <h3 class="text-white text-lg font-semibold mb-2">logo</h3>

      <!-- Buttons -->
      <div class="flex space-x-4">
        <button
          class="bg-white bg-opacity-80 hover:bg-opacity-100 rounded-full p-2"
        >
          ❤️
        </button>
        <button
          class="bg-white bg-opacity-80 hover:bg-opacity-100 rounded-full p-2"
        >
          💬
        </button>
      </div>
    </div>
</div>
</template>
<script>
import logo from "@/assets/logo.png";
export default{
    name: 'ArtCard',
    data() {
    return {
      logo,
    };
    },
}
</script>