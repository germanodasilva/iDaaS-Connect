[200~FROM registry.access.redhat.com/ubi8/ubi

#
# Metadata information
#

LABEL name="iDaas Connect EDI" \
      vendor="Red Hat" \
      maintainer="Alan Scott <alscott@redhat.com>" \
      build-date="2021-03-29" \
      version="${IDAAS_CONNECT_EDI}" \
      release="1"

#
# Environment variables used for build/exec
#

ENV IDAAS_CONNECT_EDI=1.0.0 \
    IDAAS_CONNECT_EDI_USER=idaas-edi \
    IDAAS_CONNECT_EDI_WEB_PORT=8080 \
    IDAAS_CONNECT_EDI_APP_NAME=idaas-connect.edi.jar \
    IDAAS_CONNECT_EDI_WORKDIR=/opt/idaas-connect-edi \
    YUM_OPTS="--setopt=install_weak_deps=False --setopt=tsflags=nodocs"

#
# Copy binary to image
#

COPY source/target/iDAAS-Connect-EDI-1.0-SNAPSHOT.jar  /opt/idaas-connect-edi/idaas-connect.edi.jar

#
# Install requirements and application
#

RUN yum install ${YUM_OPTS} -y java-11-openjdk-devel
#    yum -y clean all && \
#    cd ${ETHERPAD_BASEDIR} && \
#    curl -L https://github.com/ether/etherpad-lite/archive/${ETHERPAD_VERSION}.tar.gz | tar -xz && \
#    mv etherpad-lite-${ETHERPAD_VERSION} ${ETHERPAD_WORKDIR} && \
#    mkdir ${ETHERPAD_DATADIR} && \
#    cd ${ETHERPAD_WORKDIR}

#
# Prepare the image for running on OpenShift
#

RUN groupadd idaas && \
    useradd -m -g idaas ${IDAAS_CONNECT_EDI_USER} && \
    chown ${IDAAS_CONNECT_EDI_USER}.idaas -R ${IDAAS_CONNECT_EDI_WORKDIR} && \
    chmod -R g+rwX ${IDAAS_CONNECT_EDI_WORKDIR}

USER ${IDAAS_CONNECT_EDI_USER}

RUN export PATH=$PATH:/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.282.b08-2.el8_3.x86_64

RUN ls -l /opt/idaas-connect-edi/
#
# Set application execution parameters
#

EXPOSE ${IDAAS_CONNECT_EDI_WEB_PORT}
WORKDIR ${IDAAS_CONNECT_EDI_WORKDIR}

ENTRYPOINT exec java -jar idaas-connect.edi.jar
