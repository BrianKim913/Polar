# Build
custom_build(
    #컨테이너 이미지 이름
    ref = 'catalog-service',
    #빌드하기 위한 커맨드
    command = './gradlew bootBuildImage --imageName $EXPECTED_REF',
    #files to watch? 빌드를 트리거하는 파일들
    deps = ['build.gradle', 'src']
)

# Deploy
k8s_yaml(['k8s/deployment.yml', 'k8s/service.yml'])

# Manage
k8s_resource('catalog-service', port_forwards=['9001:9001'])