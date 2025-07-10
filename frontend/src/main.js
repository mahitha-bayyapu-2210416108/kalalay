import { createApp } from 'vue'
import App from './App.vue'
import { provideApollo } from './apollo'

const app = createApp(App)
provideApollo(app)
app.mount('#app')
