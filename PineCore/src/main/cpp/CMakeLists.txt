cmake_minimum_required(VERSION 3.4.1)

set(CMAKE_CXX_STANDARD 17)
set(CMAKE_POSITION_INDEPENDENT_CODE ON)

set(C_FLAGS "-Wall -Wextra -fvisibility=hidden -fvisibility-inlines-hidden -fno-exceptions -fno-rtti -flto=thin")
set(LINKER_FLAGS "-fuse-ld=lld -flto=thin -ffixed-x18 -Wl,--hash-style=both -Wl,--unresolved-symbols=ignore-all")

set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} ${C_FLAGS}")
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} ${C_FLAGS}")

set(CMAKE_SHARED_LINKER_FLAGS "${CMAKE_SHARED_LINKER_FLAGS} ${LINKER_FLAGS}")
set(CMAKE_MODULE_LINKER_FLAGS "${CMAKE_MODULE_LINKER_FLAGS} ${LINKER_FLAGS}")
set(CMAKE_EXE_LINKER_FLAGS "${CMAKE_EXE_LINKER_FLAGS} ${LINKER_FLAGS}")

set(PINE_SOURCES
        pine.cpp
        ruler.cpp
        android.cpp
        jni_bridge.cpp
        art/art_method.cpp
        art/thread.cpp
        art/jit.cpp
        trampoline/trampoline_installer.cpp
        utils/memory.cpp
        utils/scoped_memory_access_protection.cpp
        utils/elf_img.cpp
        utils/well_known_classes.cpp)

if (CMAKE_ANDROID_ARCH_ABI STREQUAL "armeabi-v7a")
    set(CMAKE_ASM_FLAGS "${CMAKE_ASM_FLAGS} -arch armv7")
    set(PINE_SOURCES
            ${PINE_SOURCES}
            trampoline/arch/thumb2.cpp
            trampoline/arch/thumb2.S)
elseif(CMAKE_ANDROID_ARCH_ABI STREQUAL "arm64-v8a")
    set(CMAKE_ASM_FLAGS "${CMAKE_ASM_FLAGS} -arch arm64")
    set(PINE_SOURCES
            ${PINE_SOURCES}
            trampoline/arch/arm64.cpp
            trampoline/arch/arm64.S)
elseif(CMAKE_ANDROID_ARCH_ABI STREQUAL "x86")
    set(CMAKE_ASM_FLAGS "${CMAKE_ASM_FLAGS} -arch x86")
    set(PINE_SOURCES
            ${PINE_SOURCES}
            trampoline/arch/x86.cpp)
else()
    message(FATAL_ERROR "Unsupported cpu abi ${CMAKE_ANDROID_ARCH_ABI} (Only armeabi-v7a and arm64-v8a are now supported)!")
endif()

add_library(pine SHARED ${PINE_SOURCES})

find_library(log-lib log)
#find_package(cxx REQUIRED CONFIG)

#add_library(cxx STATIC IMPORTED)
#set_target_properties(cxx PROPERTIES IMPORTED_LOCATION ${CMAKE_CURRENT_SOURCE_DIR}/libs/${ANDROID_ABI}/libcxx.a)

target_link_libraries(pine ${log-lib})

ENABLE_LANGUAGE(ASM)
