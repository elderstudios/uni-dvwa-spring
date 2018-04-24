# -*- mode: ruby -*-
# vi: set ft=ruby :

Vagrant.configure("2") do |config|
  config.vm.box_check_update =  false
  config.vm.box = "/Users/m/numisec/CentOS-7-x86_64-Vagrant-1710_01.VirtualBox.box"
  config.vm.network "private_network", ip: "192.168.56.10"
  config.vm.hostname = 'dvwa.spring'
  config.vm.synced_folder './data', '/vagrant', disabled: true
  config.vm.provider "virtualbox" do |vb|
    vb.gui = false
    vb.memory = "2048"
  end
  config.vm.provision "shell" do |shell|
    shell.privileged = true
    shell.path = "vagrant-provision.sh"
  end
end
