I put "−" sign in points, when i haven't got any comment.

# Functional requirements
1. Task is done, I thought about pressed state. For now, I leave project with little strange behavior. Last clicked element is highlighted even when user back to list. It isn't appropriate from a user point of view, but it's only done for preview purpose. Anyway, it looks correct on tablet when landscape view is loaded.
2. −
3. −
4. Application is using BroadcastReceiver to for detecting connectivity changes. It should reload automatically when network is back.

# Technical requirements

1. Whole app is written in Kotlin
2. −
3. −
4. I used MVVM because I am more familiar with it and I was able to finish task faster. I hope that isn't any issue while tasks say about MVP or similar approach.
5. −
6. −
7. −
8. −
9. −

# Additional requirements
1. Done
2. I used RxJava with Retrofit, yet it is very simple use case and most of app uses LiveData instead.
3. Done for DetailsViewModel
4. ListAdapter and ListItemViewHolder are classes written in Java
5. I set logs in interceptors for debug flavour