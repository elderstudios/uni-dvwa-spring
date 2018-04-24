#!/usr/bin/env bash

whoami

# Update package
sudo yum update -y
sudo yum clean all

# Install java
sudo yum install -y java-1.8.0-openjdk java-1.8.0-openjdk-devel
java -version
which java
readlink -f $(which java)

# Install jenkins
sudo yum install -y wget git
sudo wget -O /etc/yum.repos.d/jenkins.repo https://pkg.jenkins.io/redhat-stable/jenkins.repo
sudo rpm --import https://pkg.jenkins.io/redhat-stable/jenkins.io.key
sudo yum install -y jenkins

## Start and enable jenkins
sed -i '/JENKINS_USER=.*/c\JENKINS_USER="root"' /etc/sysconfig/jenkins
sed -i '/JENKINS_PORT=.*/c\JENKINS_PORT="8282"' /etc/sysconfig/jenkins
sudo systemctl start jenkins
sudo systemctl enable jenkins
sudo systemctl status jenkins
open http://192.168.56.10:8282
sudo cat /var/lib/jenkins/secrets/initialAdminPassword

# Install docker engine
sudo yum remove -y docker docker-client docker-client-latest docker-common docker-latest docker-latest-logrotate docker-logrotate docker-selinux docker-engine-selinux docker-engine
sudo yum install -y yum-utils device-mapper-persistent-data lvm2
sudo yum-config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo
sudo yum repolist
sudo yum install -y docker-ce

## Start and enable docker
sudo systemctl start docker
sudo systemctl enable docker
sudo systemctl status docker
docker --version


# Install  maven
wget http://www-eu.apache.org/dist/maven/maven-3/3.5.3/binaries/apache-maven-3.5.3-bin.tar.gz
tar -xf apache-maven-3.5.3-bin.tar.gz
rm -rf apache-maven-3.5.3-bin.tar.gz


# Set global environment
MVN_HOME=/home/vagrant/apache-maven-3.5.3
JAVA_HOME=`readlink -f $(which java)`
export PATH=$PATH:$MVN_HOME/bin
