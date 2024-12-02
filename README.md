# Compose Shadow

A Kotlin Multiplatform implementation of drop/inner shadows for Compose Multiplatform.

This library will continue to be maintained until this is officially supported in Compose.

![Maven Central Version](https://img.shields.io/maven-central/v/com.adamglin/compose-shadow)

---

## Versions

| `compose-shadow` | CMP           | Kotlin |
|------------------|---------------|--------|
| 2.0.1            | 1.7.1         | 2.1.0  |
| 2.0.0            | 1.7.1         | 2.0.21 |
| 1.0.0            | 1.7.0-rc01    | 2.0.21 |

## Installation

```kts
implementation("com.adamglin:compose-shadow:$version")
```

## Platform support

`compose-shadow` supports these platforms:

1. [x] Android (SDK > 28)
2. [x] iOS
3. [x] Desktop (JVM)
4. [x] JS/Wasm

## Usage

Apply and customize shadow using the `dropShadow` Modifier:

### Drop Shadow
```kotlin
Box(
    Modifier
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
### Inner Shadow
```kotlin
Box(
    Modifier
        .innerShadow(
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
## Sample
We provided this address [here](https://adamglin0.github.io/compose-shadow/sample/index.html) for viewing the wasm online examples.

### Screenshots
<img width="300" alt="image" src="https://github.com/user-attachments/assets/36d15219-d4ea-4de9-84fe-df2cbceb0e2e">
<img width="300" alt="image" src="https://github.com/user-attachments/assets/cf1b42d1-5d92-4259-93a6-f0883b7d9dc7">
<img width="300" alt="image" src="https://github.com/user-attachments/assets/52f1bc30-2315-4569-bbb9-4bbb385ab07c">


