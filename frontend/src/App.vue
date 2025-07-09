<template>
  <div class="p-4">
    <h1>Kalālay Artworks</h1>
    <div v-if="loading">Loading...</div>
    <div v-else-if="error">Error loading artworks.</div>
    <ul>
      <li v-for="art in data?.allArtworks" :key="art.id">
        <strong>{{ art.title }}</strong> — Likes: {{ art.likes }}
        <button @click="like(art.id)">❤️</button>
      </li>
    </ul>
  </div>
</template>

<script setup>
import { useQuery, useMutation } from '@vue/apollo-composable';
import { GET_ALL_ARTWORKS, LIKE_ARTWORK } from './graphql/queries';

const { result: data, loading, error, refetch } = useQuery(GET_ALL_ARTWORKS);
const { mutate: likeArtwork } = useMutation(LIKE_ARTWORK);

const like = async (id) => {
  await likeArtwork({ id });
  refetch();
};
</script>
