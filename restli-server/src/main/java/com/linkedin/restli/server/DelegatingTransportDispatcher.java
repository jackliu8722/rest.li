/*
   Copyright (c) 2012 LinkedIn Corp.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/

package com.linkedin.restli.server;

import java.util.Map;

import com.linkedin.r2.message.RequestContext;
import com.linkedin.r2.message.rest.RestException;
import com.linkedin.r2.message.rest.RestRequest;
import com.linkedin.r2.message.rest.RestResponse;
import com.linkedin.r2.message.rest.RestStatus;
import com.linkedin.r2.message.rpc.RpcRequest;
import com.linkedin.r2.message.rpc.RpcResponse;
import com.linkedin.r2.transport.common.RestRequestHandler;
import com.linkedin.r2.transport.common.bridge.common.TransportCallback;
import com.linkedin.r2.transport.common.bridge.common.TransportResponseImpl;
import com.linkedin.r2.transport.common.bridge.server.TransportCallbackAdapter;
import com.linkedin.r2.transport.common.bridge.server.TransportDispatcher;

/**
 * Delegates dispatch to a single {@link RestRequestHandler}.
 *
 * @author dellamag
 */
public class DelegatingTransportDispatcher implements TransportDispatcher
{
  private final RestRequestHandler _handler;

  public DelegatingTransportDispatcher(final RestRequestHandler handler)
  {
    _handler = handler;
  }

  @Override
  public void handleRestRequest(final RestRequest req,
                                final Map<String, String> wireAttrs,
                                final RequestContext requestContext,
                                final TransportCallback<RestResponse> callback)
  {
    try
    {
      _handler.handleRequest(req, requestContext, new TransportCallbackAdapter<RestResponse>(callback));
    }
    catch (Exception e)
    {
      final Exception ex = RestException.forError(RestStatus.INTERNAL_SERVER_ERROR, e);
      callback.onResponse(TransportResponseImpl.<RestResponse>error(ex));
    }
  }

  @Override
  public void handleRpcRequest(final RpcRequest req,
                               final Map<String, String> wireAttrs,
                               final TransportCallback<RpcResponse> callback)
  {
    throw new UnsupportedOperationException();
  }
}
