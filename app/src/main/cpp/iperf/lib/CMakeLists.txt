#Cmake file for library lib

#查找当前目录下所有源文件并将名称保存到DIR_LIB_SRCS中
aux_source_directory(. DIR_LIB_SRCS)

#头文件路径
include_directories(/Users/soyx/Library/Android/sdk/ndk-bundle/sysroot/usr/include
	${PROJECT_SOURCE_DIR}/main/cpp/iperf/lib)
#库文件路径
link_directories(/Users/soyx/Library/Android/sdk/ndk-bundle/sysroot/usr/lib)
#生成链接库，默认静态
add_library(iperf-lib 
	SHARED
	${DIR_LIB_SRCS})


	# Searches for a specified prebuilt library and stores the path as a
    # variable. Because CMake includes system libraries in the search path by
    # default, you only need to specify the name of the public NDK library
    # you want to add. CMake verifies that the library exists before
    # completing its build.

    find_library( # Sets the name of the path variable.
                  log-lib

                  # Specifies the name of the NDK library that
                  # you want CMake to locate.
                  log )

    # Specifies libraries CMake should link to your target library. You
    # can link multiple libraries, such as libraries you define in this
    # build script, prebuilt third-party libraries, or system libraries.

    target_link_libraries( # Specifies the target library.
                           iperf-lib

                           # Links the target library to the log library
                           # included in the NDK.
                           ${log-lib} )