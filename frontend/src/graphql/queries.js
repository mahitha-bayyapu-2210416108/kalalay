import gql from 'graphql-tag';

export const GET_ALL_ARTWORKS = gql`
  query {
    allArtworks {
      id
      title
      shortDescription
      likes
    }
  }
`;

export const LIKE_ARTWORK = gql`
  mutation($id: ID!) {
    likeArtwork(id: $id) {
      id
      likes
    }
  }
`;

export const ADD_ARTWORK = gql`
  mutation(
    $title: String!
    $shortDescription: String
    $fullDescription: String
    $imageUrl: String
  ) {
    addArtwork(
      title: $title
      shortDescription: $shortDescription
      fullDescription: $fullDescription
      imageUrl: $imageUrl
    ) {
      id
      title
    }
  }
`;
