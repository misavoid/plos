# notes for later

Add a deploy job to .gitlab-ci.yml that SSH’s into nano and runs your compose commands. For example:
```yaml
deploy:
stage: deploy
image: alpine:latest
before_script:
- apk add --no-cache openssh-client
script:
- mkdir -p ~/.ssh
- echo "$SSH_PRIVATE_KEY" > ~/.ssh/id_rsa
- chmod 600 ~/.ssh/id_rsa
- ssh-keyscan nano420.de >> ~/.ssh/known_hosts
- ssh your-user@nano420.de 'cd /opt/personal-life-os && docker-compose pull && docker-compose up -d'
only:
- main
```
That way, every time you push to main, CI will log into your server and restart your stack.