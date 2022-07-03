> ## otus_java_2022_06

> ### hw01-gradle

#### 1. Указание версии дистрибутива для gradlew

Указывается в файле _gradle/wrapper/gradle-wrapper.properties_

#### 2. buildSrc

_buildSrc_ - это служебный проект (модуль), который собирается первым. Таким образом, все содержимое _
buildSrc/build/libs/buildSrc.jar_ подкладывается в _Сlasspath_ проекта. В результате все классы и интерфейсы, собранные
в _buildSrc.jar_ будут доступны всему проекту.

#### 3. Использование buildSrc для указания версий в build.gradle

Одним из практических применений _buildSrc_ является указание всех версий заивисмостей для _build.gradle_ внутри _
buildSrc_. В данном примере, зависимость для guava подгружается синтаксисом:

```groovy
dependencies {
    dependency "com.google.guava:guava:${Versions.guava}"
}
```

Здесь ```${Versions.guava}``` подгружается из служебного модуля _buildSrc_.

#### 4. Сборка "толстого" jar'а

Одним из вариантов сборки "толстого" jar'а является применение плагина ```johnrengelman.shadow```:

```groovy
plugins {
    id 'com.github.johnrengelman.shadow' version '7.1.2' apply false
}
```

```apply false```  означает, что этот плагин не нужно применять к данному модулю (проекту) **(not to apply the plugin to
the current project)**

#### 5. Добавление нового модуля (проекта Gradle) в основной проект

В файле _settings.gradle_ нужно добавить команду вида ```include 'hw01-gradle'```, в которой
_'hw01-gradle'_ - это название модуля (проекта Gradle)