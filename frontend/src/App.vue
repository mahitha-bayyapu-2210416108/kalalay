<template>
  <div class="p-4">
    <NavBar/>
    <ArtCard/>

  </div>
    
</template>
<script>
import NavBar from '@/components/NavBar.vue';
import ArtCard from '@/components/ArtCard.vue'
export default {
  name: 'App',
  components: {
    NavBar,
    ArtCard
  },
  
}
</script>