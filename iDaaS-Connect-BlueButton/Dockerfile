FROM registry.access.redhat.com/ubi8/ubi

#
# Metadata information
#

LABEL name="iDaas Connect BlueButton" \
      vendor="Red Hat" \
      maintainer="Alan Scott <alscott@redhat.com>" \
      maintainer="Germano Da Silva <gedasilv@redhat.com>" \
      build-date="2021-04-11" \
      version="${IDAAS_CONNECT_BLUEBUTTON}" \
      release="1"

#
# Environment variables used for build/exec
#

ENV IDAAS_CONNECT_BLUEBUTTON=1.0.0 \
    IDAAS_CONNECT_BLUEBUTTON_USER=idaas-edi \
    IDAAS_CONNECT_BLUEBUTTON_WEB_PORT=8892 \
    IDAAS_CONNECT_BLUEBUTTON_APP_NAME=idaas-connect.bluebutton.jar \
    IDAAS_CONNECT_BLUEBUTTON_WORKDIR=/opt/idaas-connect-bluebutton \
    YUM_OPTS="--setopt=install_weak_deps=False --setopt=tsflags=nodocs"

#
# Copy binary to image
#

COPY source/*  /opt/idaas-connect-bluebutton/

#
# Fixed vulnerability flaws
#
RUN yum update -y

#
# Install requirements and application
#

RUN yum install ${YUM_OPTS} -y java-11-openjdk-devel
RUN dnf -y install maven

#
# Prepare the image for running on OpenShift
#

RUN groupadd idaas && \
    useradd -m -g idaas ${IDAAS_CONNECT_BLUEBUTTON_USER} && \
    chown ${IDAAS_CONNECT_BLUEBUTTON_USER}.idaas -R ${IDAAS_CONNECT_BLUEBUTTON_WORKDIR} && \
    chmod -R g+rwX ${IDAAS_CONNECT_BLUEBUTTON_WORKDIR}

USER ${IDAAS_CONNECT_BLUEBUTTON_USER}

RUN export PATH=$PATH:/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.282.b08-2.el8_3.x86_64

RUN cd ${IDAAS_CONNECT_BLUEBUTTON_WORKDIR} && \
    mvn -e clean package 

#RUN ls -l /opt/idaas-connect-bluebutton/
#
# Set application execution parameters
#

EXPOSE ${IDAAS_CONNECT_BLUEBUTTON_WEB_PORT}
WORKDIR ${IDAAS_CONNECT_BLUEBUTTON_WORKDIR}

ENTRYPOINT exec java -jar idaas-connect.edi.jar
