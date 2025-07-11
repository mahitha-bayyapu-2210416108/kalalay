import { createApp } from 'vue'
import App from './App.vue'
import { provideApollo } from './apollo'
import './main.css';

const app = createApp(App)
provideApollo(app)
app.mount('#app')
