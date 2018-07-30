



#  引入 gradle 配置文件


![MoviesApplication](https://github.com/oleja00/MoviesApplication)


 `def tree = fileTree(dir: 'dependencies', include: '**/*.gradle')
    tree.each {
        File file -> apply from: file, to: dependencies
    }`
