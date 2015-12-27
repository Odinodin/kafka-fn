Vagrant.configure(2) do |config|

  config.vm.box = "ubuntu/trusty64"

  config.vm.provider :virtualbox do |v|
    v.name = "kafka1"
    v.memory = 2048
  end

  config.vm.hostname = "kafka1"
  config.vm.network :private_network, ip: "192.168.33.21"

  # Disable the new default behavior introduced in Vagrant 1.7, to
  # ensure that all Vagrant machines will use the same SSH key pair.
  # See https://github.com/mitchellh/vagrant/issues/5005
  config.ssh.insert_key = false

  # Set the name of the VM. See: http://stackoverflow.com/a/17864388/100134
  config.vm.define :kafka1 do |kafka1|
  end

  config.vm.provision "ansible" do |ansible|
    ansible.verbose = "v"
    ansible.playbook = "playbook.yml"
  end
end
