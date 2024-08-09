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

<img width="300" alt="image" src="https://github.com/user-attachments/assets/36d15219-d4ea-4de9-84fe-df2cbceb0e2e">
<img width="300" alt="image" src="https://github.com/user-attachments/assets/cf1b42d1-5d92-4259-93a6-f0883b7d9dc7">
<img width="300" alt="image" src="https://github.com/user-attachments/assets/52f1bc30-2315-4569-bbb9-4bbb385ab07c">


