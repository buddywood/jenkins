FROM jenkins/jenkins:lts-jdk17

USER root

# Configure Timezone
RUN \
    rm -f /etc/localtime && \
    ln -s /usr/share/zoneinfo/America/Chicago /etc/localtime && \
    echo 'America/Chicago' > /etc/timezone && \
    dpkg-reconfigure --frontend noninteractive tzdata

# Install Maven on Jenkins image
RUN apt-get update && \
    apt-get install -y maven

# Install Docker on Jenkins image
RUN apt-get update && \
    apt-get install -y apt-transport-https ca-certificates curl gnupg lsb-release && \
    curl -fsSL https://download.docker.com/linux/debian/gpg | gpg --dearmor -o /usr/share/keyrings/docker-archive-keyring.gpg && \
    echo "deb [arch=$(dpkg --print-architecture) signed-by=/usr/share/keyrings/docker-archive-keyring.gpg] https://download.docker.com/linux/debian $(lsb_release -cs) stable" | tee /etc/apt/sources.list.d/docker.list > /dev/null && \
    apt-get update && apt-get install -y docker-ce-cli

# Copy the private key file to the Jenkins user's home directory
COPY id_rsa /var/jenkins_home/.ssh/id_rsa

# Set appropriate ownership and permissions for the private key file
RUN chown jenkins:jenkins /var/jenkins_home/.ssh/id_rsa && \
    chmod 400 /var/jenkins_home/.ssh/id_rsa

COPY plugins.txt /usr/share/jenkins/ref/plugins.txt

RUN jenkins-plugin-cli --plugin-file /usr/share/jenkins/ref/plugins.txt

# Set environment variables
ENV SSH_USERNAME="buddywood"
ENV SSH_KEY_PASSWORD=""
ENV SSH_PRIVATE_FILE_PATH=/var/jenkins_home/.ssh/id_rsa

# Copy JCasC configuration file to the Jenkins user's home directory
COPY jenkins-configuration.yaml /var/jenkins_home/jenkins-configuration.yaml
ENV CASC_JENKINS_CONFIG /var/jenkins_home/jenkins-configuration.yaml

COPY seedJob.xml /usr/share/jenkins/ref/jobs/seed-job/config.xml

ENV JAVA_OPTS -Djenkins.install.runSetupWizard=false

USER jenkins

# Install Docker as Jenkins user
RUN apt-get update && \
    apt-get install -y docker.io && \
    usermod -a -G docker jenkins

