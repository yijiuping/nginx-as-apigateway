/*
* Converts the output of the NGINX stub_status module to Prometheus format so that basic metrics can be scraped from a /metrics endpoint.
* */
var connection_vars = ['active', 'reading', 'writing', 'waiting'];
var server_vars =     ['accepts', 'handled', 'requests'];
var res = '';

function metrics(r, data, flags) {
    res += data;      // Collect the entire response,
    if (flags.last) { //  until we get the last byte.
        var metrics  = [];
        connection_vars.forEach(m => metrics.push(`connections{${m}} ${r.variables['connections_' + m]}`));
        var server_vals = res.split('\n')[2].trim().split(' ');
        for (var i = 0; i < server_vars.length; i++) {
            metrics.push(`server{${server_vars[i]}} ${server_vals[i]}`);
        }
        r.sendBuffer(metrics.join('\n') + '\n', flags);
    }
}

function nolength(r) {
    delete r.headersOut['Content-Length'];
}

export default { metrics, nolength }
