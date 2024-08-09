# Compose Shadow

a kotlin platform library for show drop shadow in compose.

![Maven Central Version](https://img.shields.io/maven-central/v/com.adamglin/compose-shadow)

---

## Install

compose-shadow supports platforms below.

1. [x] android
2. [x] ios
3. [x] desktop(jvm)

#### in your kotlin  module

```kotlin
implementation("com.adamglin:compose-shadow:$version")
```


## Using

add dropShadow by `Modifier`.
```kotlin
Box(
    Modifier
        .size(50.dp)
        .dropShadow(
            shape = RectangleShape,
            color = Color.Black.copy(.5f),
            offsetX = 4.dp,
            offsetY = 4.dp,
            blur = 10.dp,
            spread = 5.dp,
        )
        .background(Color.White),
)
```
