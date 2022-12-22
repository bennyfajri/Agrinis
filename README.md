# Test Screening
Buatlah aplikasi native *offline-first* untuk menampilkan berita menggunakan [NewsApi](https://newsapi.org).  
User stories:  
1. Buat sebuah halaman untuk menampilkan kategori berita.  
2. Tampilkan sumber berita ketika kategori diklik.  
3. Ketika sumber berita diklik tampilkan artikel berdasarkan sumber tersebut.  
4. Tampilkan detail artikel dengan menggunakan library [Android Browser Helper](https://github.com/GoogleChrome/android-browser-helper) ketika artikel di klik.  
5. Tambahkan fungsi untuk mencari sumber berita dan artikel berita.  
6. Implementasikan endless scrolling pada sumber berita dan artikel berita.  
  
## Rules  
- Lakukan git init sebelum memulai test dan gunakan readable git commit.  
- Kerjakan sesuai dengan arsitektur yang telah dibuat.  
- Silakan ikuti arahan sesuai TODO yang telah dibuat.  
- Silakan gunakan standard naming convention. ([Kotlin Coding Convention](https://kotlinlang.org/docs/coding-conventions.html#names-for-backing-properties), [Android Naming Convention](https://github.com/TreyCai/AndroidNamingConvention), [Resource File Naming Convention](https://medium.com/@ajayjg/ids-layouts-resource-file-naming-android-naming-convention-3fc16e39721d)).  
- Buat 3 buah package baru di dalam package `ui` dengan nama `category`, `source`, dan `article` yang masing-masingnya berisi Activity/Fragment, View Model, dan Adapter.  
- Buat 3 buah Repository dan DAO baru berdasarkan package diatas.  
- Buat tampilan agar *user friendly*.

## Licence
```
Copyright 2022 PT Arca International

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```