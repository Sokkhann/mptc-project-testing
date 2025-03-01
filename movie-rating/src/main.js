import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'
import naive from 'naive-ui'
import router from './router'

// Import FontAwesome
import { library } from '@fortawesome/fontawesome-svg-core'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import { faArrowRight, faBookmark } from '@fortawesome/free-solid-svg-icons'
import { faBookmark as solidBookmark } from '@fortawesome/free-solid-svg-icons'
import { faBookmark as regularBookmark } from '@fortawesome/free-regular-svg-icons'
import { faStar as solidStar, faStarHalfStroke } from '@fortawesome/free-solid-svg-icons'
import { faStar as regularStar } from '@fortawesome/free-regular-svg-icons'
import { faXmark } from '@fortawesome/free-solid-svg-icons'

// Add icons to the library
library.add(faArrowRight)
library.add(faBookmark)
library.add(solidBookmark, regularBookmark)
library.add(solidStar, faStarHalfStroke, regularStar)
library.add(faXmark)

const app = createApp(App)

app.use(naive)
app.use(router)

// Register FontAwesome globally
app.component('font-awesome-icon', FontAwesomeIcon)

app.mount('#app')
