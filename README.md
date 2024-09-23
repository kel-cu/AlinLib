<img width="120pt" src="https://cdn.kelcuprum.ru/icons/alinlib.png" align="right">

# AlinLib
Библиотека для облегчения разработки интерфейса, уведомлений и т.д.<br>
Так же содержание в одном дизайн коде
## Интеграция
[![Snapshots](https://img.shields.io/maven-metadata/v?metadataUrl=https%3A%2F%2Frepo.kelcuprum.ru%2Fsnapshots%2Fru%2Fkelcuprum%2Falinlib%2FAlinLib%2Fmaven-metadata.xml&label=Snapshots)](https://repo.kelcuprum.ru/#/snapshots/ru/kelcuprum/alinlib/AlinLib)
[![Releases](https://img.shields.io/maven-metadata/v?metadataUrl=https%3A%2F%2Frepo.kelcuprum.ru%2Freleases%2Fru%2Fkelcuprum%2Falinlib%2FAlinLib%2Fmaven-metadata.xml&label=Releases)](https://repo.kelcuprum.ru/#/releases/ru/kelcuprum/alinlib/AlinLib)

### Типы
- snapshots [Dev, alpha]
- releases [Releases, Beta, RC]
```groovy
repositories {
    maven {
        name "kelcuprum_TYPE"
        url "https://repo.kelcuprum.ru/TYPE"
    }
}
dependencies {
    modImplementation("ru.kelcuprum.alinlib:AlinLib:VERSION");
}
```
Для указания в fabric.mod.json указывайте ID: `alinlib`
## Скриншоты
### Интерфейс
![modern interface](https://cdn.modrinth.com/data/cached_images/15d5a52399c417ea741fb8d98f4e7dbccfb7dd7b_0.webp)

> Старый дизайн
> 
![Interface](https://cdn.modrinth.com/data/cached_images/d55993f79ff17145e83022ce8923f66cc492d71a.png)
### Уведомления
![Toast](https://cdn.modrinth.com/data/cached_images/4587aeeb1742db6e5000b8e4aa9dfb68fd19473f.png)
